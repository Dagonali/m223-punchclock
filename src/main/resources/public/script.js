const URL = 'http://localhost:8081';
let entries = [];

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));
    entry['category'] = formData.get('category');
    entry['room'] = formData.get('room');

    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        result.json().then((entry) => {
            entries.push(entry);
            renderEntries();
        });
    });
};


const updateEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    const numberId = new FormData(e.target);
    const number_id = numberId.get('number_id')
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));
    entry['category'] = formData.get('category');
    entry['room'] = formData.get('room');


    fetch(`${URL}/entries/${number_id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        result.json().then((entry) => {
            entries.push(entry);
            renderEntries();
        });
    });
};


const deleteEntryById = (button_id) => {
    fetch(`${URL}/entries/${button_id}`, {
        method: 'DELETE',
    }).then((result) => {
            indexEntries()
    });
}

const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const createButton = (id, state) => {
    const td = document.createElement('td')
    const button = document.createElement('button')
    switch (state) {
        case 'delete':
            button.innerText = 'delete'
            button.addEventListener('click', () => {
                deleteEntryById(id)
            })
            break;
        case 'edit':
            button.innerText = 'edit'
            button.addEventListener('click', () =>{
                updateEntry(id)
            })
            break;
    }
    td.appendChild(button)
    return td
}

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(createCell(entry.category));
        row.appendChild(createCell(entry.room));
        row.appendChild(createButton(entry.id, 'edit'));
        row.appendChild(createButton(entry.id, 'delete'));
        display.appendChild(row);
    });
};


document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    indexEntries();
});
