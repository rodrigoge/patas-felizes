CREATE TABLE pet (
    pet_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    type VARCHAR(50),
    age INT NOT NULL,
    breed VARCHAR(255) NOT NULL,
    avatar BYTEA
);

ALTER TABLE account ADD COLUMN pet_id UUID UNIQUE, ADD CONSTRAINT fk_account_pet FOREIGN KEY (pet_id) REFERENCES pet(pet_id)
ON DELETE SET NULL;
