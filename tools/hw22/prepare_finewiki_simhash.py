import argparse
import csv
import random
import re
from pathlib import Path

from datasets import load_dataset


CSV_COLUMNS = [
    "pair_id",
    "split",
    "label",
    "pair_type",
    "mutation",
    "first_document_id",
    "second_document_id",
    "first_topic",
    "second_topic",
    "first_text",
    "second_text",
]


def normalize_text(text: str) -> str:
    """
    Java-загрузчик читает CSV построчно, поэтому удаляем
    переводы строк из исходного документа.
    """
    return re.sub(r"\s+", " ", text).strip()


def prepare_document(
        text: str,
        min_length: int,
        max_length: int
) -> str | None:
    normalized = normalize_text(text)

    if len(normalized) < min_length:
        return None

    if len(normalized) > max_length:
        normalized = normalized[:max_length]

        last_space = normalized.rfind(" ")

        if last_space > 0:
            normalized = normalized[:last_space]

    return normalized.strip()


def delete_words(
        text: str,
        random_generator: random.Random,
        ratio: float = 0.05
) -> str:
    words = text.split()

    if len(words) < 20:
        return text.lower()

    delete_count = max(1, int(len(words) * ratio))

    removable_indexes = list(range(1, len(words) - 1))

    selected_indexes = set(
        random_generator.sample(
            removable_indexes,
            min(delete_count, len(removable_indexes))
        )
    )

    return " ".join(
        word
        for index, word in enumerate(words)
        if index not in selected_indexes
    )


def change_case_and_punctuation(text: str) -> str:
    result = text.lower()

    result = re.sub(
        r"[,:;!?()\[\]{}]",
        "",
        result
    )

    return normalize_text(result)


def mutate_text(
        text: str,
        random_generator: random.Random
) -> tuple[str, str]:
    mutation = random_generator.choice(
        [
            "delete_words",
            "case_punctuation",
            "combined_light",
        ]
    )

    if mutation == "delete_words":
        return (
            delete_words(
                text,
                random_generator,
                ratio=0.05
            ),
            mutation,
        )

    if mutation == "case_punctuation":
        return (
            change_case_and_punctuation(text),
            mutation,
        )

    changed = delete_words(
        text,
        random_generator,
        ratio=0.03
    )

    changed = change_case_and_punctuation(changed)

    return changed, mutation


def write_positive_pair(
        writer: csv.DictWriter,
        pair_number: int,
        document_id: str,
        text: str,
        random_generator: random.Random
) -> None:
    modified_text, mutation = mutate_text(
        text,
        random_generator
    )

    writer.writerow(
        {
            "pair_id": f"finewiki-{pair_number:08d}",
            "split": "benchmark",
            "label": 1,
            "pair_type": "near_duplicate",
            "mutation": mutation,
            "first_document_id": document_id,
            "second_document_id": (
                    document_id + "-modified"
            ),
            "first_topic": "",
            "second_topic": "",
            "first_text": text,
            "second_text": modified_text,
        }
    )


def write_negative_pair(
        writer: csv.DictWriter,
        pair_number: int,
        first_id: str,
        first_text: str,
        second_id: str,
        second_text: str
) -> None:
    writer.writerow(
        {
            "pair_id": f"finewiki-{pair_number:08d}",
            "split": "benchmark",
            "label": 0,
            "pair_type": "different_documents",
            "mutation": "none",
            "first_document_id": first_id,
            "second_document_id": second_id,
            "first_topic": "",
            "second_topic": "",
            "first_text": first_text,
            "second_text": second_text,
        }
    )


def prepare_dataset(
        output_path: Path,
        target_pairs: int,
        min_length: int,
        max_length: int,
        seed: int
) -> None:
    random_generator = random.Random(seed)

    output_path.parent.mkdir(
        parents=True,
        exist_ok=True
    )

    dataset = load_dataset(
        "HuggingFaceFW/finewiki",
        name="de",
        split="train",
        streaming=True,
    )

    pair_number = 0
    accepted_documents = 0

    previous_id: str | None = None
    previous_text: str | None = None

    with output_path.open(
            "w",
            encoding="utf-8",
            newline=""
    ) as output_file:

        writer = csv.DictWriter(
            output_file,
            fieldnames=CSV_COLUMNS,
            quoting=csv.QUOTE_MINIMAL,
        )

        writer.writeheader()

        for item in dataset:
            raw_text = item.get("text")

            if not isinstance(raw_text, str):
                continue

            text = prepare_document(
                raw_text,
                min_length,
                max_length
            )

            if text is None:
                continue

            document_id = str(
                item.get(
                    "id",
                    f"document-{accepted_documents}"
                )
            )

            accepted_documents += 1

            if pair_number < target_pairs:
                pair_number += 1

                write_positive_pair(
                    writer,
                    pair_number,
                    document_id,
                    text,
                    random_generator
                )

            if (
                    previous_id is not None
                    and previous_text is not None
                    and pair_number < target_pairs
            ):
                pair_number += 1

                write_negative_pair(
                    writer,
                    pair_number,
                    previous_id,
                    previous_text,
                    document_id,
                    text
                )

            previous_id = document_id
            previous_text = text

            if pair_number >= target_pairs:
                break

            if accepted_documents % 1_000 == 0:
                print(
                    f"Documents: {accepted_documents}, "
                    f"pairs: {pair_number}"
                )

    print()
    print("Dataset prepared")
    print(f"Output: {output_path.resolve()}")
    print(f"Pairs: {pair_number}")
    print(
        f"Accepted documents: "
        f"{accepted_documents}"
    )


def parse_arguments() -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description=(
            "Prepare a SimHash benchmark dataset "
            "from German FineWiki."
        )
    )

    parser.add_argument(
        "--output",
        type=Path,
        required=True,
    )

    parser.add_argument(
        "--pairs",
        type=int,
        default=100_000,
    )

    parser.add_argument(
        "--min-length",
        type=int,
        default=500,
    )

    parser.add_argument(
        "--max-length",
        type=int,
        default=5_000,
    )

    parser.add_argument(
        "--seed",
        type=int,
        default=22,
    )

    return parser.parse_args()


def main() -> None:
    arguments = parse_arguments()

    if arguments.pairs <= 0:
        raise ValueError(
            "Number of pairs must be positive."
        )

    if arguments.min_length <= 0:
        raise ValueError(
            "Minimum length must be positive."
        )

    if (
            arguments.max_length
            < arguments.min_length
    ):
        raise ValueError(
            "Maximum length must not be smaller "
            "than minimum length."
        )

    prepare_dataset(
        output_path=arguments.output,
        target_pairs=arguments.pairs,
        min_length=arguments.min_length,
        max_length=arguments.max_length,
        seed=arguments.seed,
    )


if __name__ == "__main__":
    main()