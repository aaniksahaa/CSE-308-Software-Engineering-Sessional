import csv
import random
import string

def generate_passport_number():
    return ''.join(random.choices(string.ascii_uppercase + string.digits, k=9))

def generate_name():
    first_names = ['John', 'Jane', 'Michael', 'Emily', 'William', 'Sophia', 'James', 'Olivia', 'Daniel', 'Emma']
    last_names = ['Smith', 'Johnson', 'Brown', 'Jones', 'Garcia', 'Miller', 'Davis', 'Rodriguez', 'Martinez', 'Wilson']
    return random.choice(first_names) + ' ' + random.choice(last_names)

def generate_data(num_entries):
    data = []
    for _ in range(num_entries):
        passport_no = generate_passport_number()
        name = generate_name()
        data.append((passport_no, name))
    return data

def write_to_csv(data, filename):
    with open(filename, 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        #writer.writerow(['Passport Number', 'Name'])
        writer.writerows(data)

if __name__ == "__main__":
    num_entries = 50
    data = generate_data(num_entries)
    write_to_csv(data, 'random_passport_data.csv')
