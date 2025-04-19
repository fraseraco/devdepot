import json
import sys

def sql_value(val, is_str=False):
    if val is None:
        return "NULL"
    if is_str:
        return "'" + str(val).replace("'", "''") + "'"
    return str(val)

def parse_json_to_sql(input_file, output_file):
    with open(input_file, "r") as f:
        data = json.load(f)

    insert_statements = []
    for item in data:
        specs = json.dumps(item.get("specifications", {})).replace("'", "''")
        values = [
            sql_value(item.get("name"), is_str=True),
            sql_value(item.get("category"), is_str=True),
            sql_value(item.get("brand"), is_str=True),
            str(item.get("inventory_qty", 0)),
            str(item.get("price", 0.0)),
            str(item.get("sku", 0)),
            f"'{specs}'"
        ]
        sql = (
            "INSERT INTO product (name, category, brand, inventory_qty, price, sku, specifications) "
            f"VALUES ({', '.join(values)});"
        )
        insert_statements.append(sql)

    with open(output_file, "w") as out:
        out.write("\n".join(insert_statements))
    print(f"SQL output written to {output_file}")

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python parse_universal.py input.json output.sql")
    else:
        parse_json_to_sql(sys.argv[1], sys.argv[2])
