CREATE TABLE IF NOT EXISTS Receipt (
    id INTEGER AUTO_INCREMENT,
    description TEXT NOT NULL,
    date_created TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Item (
    id INTEGER AUTO_INCREMENT,
    receipt_id INTEGER NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);