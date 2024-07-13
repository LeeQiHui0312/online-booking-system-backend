-- Create user table
CREATE TABLE t_user (
    id uuid NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    is_sys VARCHAR(2) NOT NULL DEFAULT 'N',
    CONSTRAINT t_user_pkey PRIMARY KEY (id)
);

-- Create car table
CREATE TABLE t_car (
    id uuid NOT NULL,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL,
    daily_price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT t_car_pkey PRIMARY KEY (id)
);

-- Create booking table
CREATE TABLE t_booking (
    id uuid NOT NULL,
    code VARCHAR(100) NOT NULL,
    user_id uuid NOT NULL,
    car_id uuid NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT t_booking_pkey PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES t_user(id),
    FOREIGN KEY (car_id) REFERENCES t_car(id)
);
