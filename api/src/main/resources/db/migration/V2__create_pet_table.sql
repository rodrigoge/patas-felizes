CREATE TABLE pet (
    pet_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    type VARCHAR(50),
    age INT NOT NULL,
    breed VARCHAR(255) NOT NULL,
    avatar BYTEA,
    giver_id UUID NOT NULL,
    receiver_id UUID,
    CONSTRAINT fk_giver FOREIGN KEY (giver_id) REFERENCES account(account_id),
    CONSTRAINT fk_receiver FOREIGN KEY (receiver_id) REFERENCES account(account_id)
);
