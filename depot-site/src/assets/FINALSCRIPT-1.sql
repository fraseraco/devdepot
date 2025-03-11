CREATE TABLE Departments (
    department_name VARCHAR(50) NOT NULL,
    department_code VARCHAR(4) PRIMARY KEY NOT NULL,
    office_number VARCHAR(4) NOT NULL,
    office_phone VARCHAR(12) NOT NULL,
    department_head VARCHAR(9) NOT NULL
);


CREATE TABLE Doctors (
    doctor_id VARCHAR(9) PRIMARY KEY NOT NULL,
    ssn VARCHAR(11) UNIQUE NOT NULL,
    assigned_department VARCHAR(4),
    FOREIGN KEY (assigned_department) REFERENCES Departments(department_code),
    first_name VARCHAR(50) NOT NULL,
    middle_init VARCHAR(1),
    last_name VARCHAR(25),
    address VARCHAR(60),
    phone_no VARCHAR(12),                    -- "xxx-xx-xxxx"
    birth_date VARCHAR(10) NOT NULL,                  -- "MM-DD-YYYY"
    contact_no VARCHAR(12) NOT NULL,
    
); 

CREATE TABLE Patients (
    patient_id VARCHAR(9) PRIMARY KEY,       -- "pXXXXXXXX"
    first_name VARCHAR(100) NOT NULL,
    middle_initial VARCHAR(1),
    last_name VARCHAR(100) NOT NULL,
    ssn VARCHAR(11) UNIQUE NOT NULL,         -- "AAA-GG-SSS" 
    current_address VARCHAR(60),
    permanent_address VARCHAR(60),
    permanent_phone VARCHAR(12),             -- "xxx-xxx-xxxx"
    phone_no VARCHAR(12),                    -- "xxx-xx-xxxx"
    birth_date VARCHAR(10) NOT NULL,                  -- "MM-DD-YYYY"
    sex VARCHAR(3),
    condition VARCHAR(20) NOT NULL,
    -- MAKE SURE YOU FIGURE OUT HOW TO HANDLE PROCEDURES    
    primary_care_doctor_id VARCHAR(9) NOT NULL,            -- foreign key
    secondary_care_doctor_id VARCHAR(9),          -- foreign key, not required
    FOREIGN KEY (primary_care_doctor_id) REFERENCES Doctors(doctor_id),
    FOREIGN KEY (secondary_care_doctor_id) REFERENCES Doctors(doctor_id)
);

CREATE TABLE Interaction (
    patient_id VARCHAR(9) NOT NULL,
    interaction_ID VARCHAR(12),
    interaction_date VARCHAR(12),
    interaction_time VARCHAR(12),
    interaction_description VARCHAR(200),
    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id)
);


CREATE TABLE Procedure (
    procedure_name VARCHAR(30),
    procedure_description VARCHAR(200),
    procedure_number VARCHAR(7) PRIMARY KEY NOT NULL, --AAA1111
    procedure_duration VARCHAR(12),
    offering_department VARCHAR(20),
    FOREIGN KEY (offering_department) REFERENCES Departments(department_code)
);

CREATE TABLE Patient_Procedure (
    procedure_patient VARCHAR(9),
    patient_procedure_number VARCHAR(7),
    notes VARCHAR(200),
    PRIMARY KEY (procedure_patient, patient_procedure_number),
    FOREIGN KEY (procedure_patient) REFERENCES Patients(patient_id),
    FOREIGN KEY (patient_procedure_number) REFERENCES Procedure(procedure_number),
    procedure_date VARCHAR(20),
    procedure_time VARCHAR(12)
);

CREATE TABLE Patient_Procedure_Doctors (
    patient_id VARCHAR(9),
    procedure_id VARCHAR(9),
    procedure_doctor VARCHAR(9),
    notes Varchar (100),
    PRIMARY KEY (patient_id, procedure_id, procedure_doctor),
    FOREIGN KEY (procedure_patient) REFERENCES Patients(patient_id),
    FOREIGN KEY (procedure_doctor) REFERENCES Doctors(doctor_id),
    FOREIGN KEY (procedure_id) REFERENCES Procedure(procedure_id)
);

CREATE TABLE Medication (
    med_name VARCHAR(30) PRIMARY KEY NOT NULL,
    med_manufacturer VARCHAR(30) UNIQUE NOT NULL,
    med_description VARCHAR(30) UNIQUE NOT NULL
    -- MIGHT NEED THE PATIENT THIS IS PRESCRIBED TOO
);

CREATE TABLE Patient_Medication (
    patient_id VARCHAR(9),
    med_name VARCHAR(30),
    prescribing_doctor VARCHAR(9),
    prescription_date DATE,
    PRIMARY KEY (patient_id, med_name, prescription_date),
    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id),
    FOREIGN KEY (med_name) REFERENCES Medication(med_name),
    FOREIGN KEY (prescribing_doctor) REFERENCES Doctors(doctor_id)
);
