let insertButton = document.getElementById('insert-new-book');
insertButton.onclick = function() {
  let xhr = new XMLHttpRequest();
  xhr.open('GET', '/lib?method=insert', false);
  xhr.send();
  if (xhr.status != 200) {
    alert( xhr.status + ': ' + xhr.statusText );
  } else {
    alert( xhr.responseText );
  }
};

let viewAllButton = document.getElementById('view-all-book');
viewAllButton.onclick = function() {
  let xhr = new XMLHttpRequest();
  xhr.open('GET', '/lib?method=viewall', false);
  xhr.send();
  if (xhr.status != 200) {
    alert( xhr.status + ': ' + xhr.statusText );
  } else {
    let json = xhr.responseText;
    alert(json);
    let library = JSON.parse(json);
    workWithTable(library);
  }
};

//let table = document.getElementById('library');
document.body.onclick = function(event) {
  if (event.target.nodeName != 'A') return;
  let href = event.target.getAttribute('href');
  let xhr = new XMLHttpRequest();
  xhr.open('GET', href, false);
  xhr.send();
  if (xhr.status != 200) {
    alert( xhr.status + ': ' + xhr.statusText );
  } else {
    alert( xhr.responseText );
  }

  return false;
};




function workWithTable(library) {
  let table = new Table(library);
  let oldTable = document.getElementById('library');
  if (!oldTable) {
    document.body.appendChild(table.create());
  } else {
    document.body.replaceChild(table.create(), oldTable);
  }
}

function Table(library) {
  var table = document.createElement('table');

  function create() {
    renderFirstRow();
    for (let key in library) {
      renderRow (library[key]);
    }
    table.id = 'library';
    return table;
  }

  function renderFirstRow() {
    let tr = document.createElement('tr');
    let tdId = document.createElement('td');
    tdId.innerHTML = "id";
    tr.appendChild(tdId);
    let tdName = document.createElement('td');
    tdName.innerHTML = "name";
    tr.appendChild(tdName);
    let tdDate = document.createElement('td');
    tdDate.innerHTML = "date";
    tr.appendChild(tdDate);
    let tdAvailability = document.createElement('td');
    tdAvailability.innerHTML = "availability";
    tr.appendChild(tdAvailability);
    let tdActions = document.createElement('td');
    tdActions.innerHTML = "actions";
    tr.appendChild(tdActions);
    table.appendChild(tr);
  }

  function renderRow(book) {
    let tr = document.createElement('tr');
    for (let key in book) {
      if (book[key].year) {
        let td = document.createElement('td');
        let date = book[key].year+ '.' + book[key].month + '.' + book[key].day;
        td.innerHTML = date;
        td.classList.add("date");
        tr.appendChild(td);
      } else {
        let td = document.createElement('td');
        td.innerHTML = book[key];
        td.classList.add(key);
        tr.appendChild(td);
      }
    }
    let td = document.createElement('td');
    td.innerHTML = '<a class="delete" href="/lib?method=delete&id=' +
    book.id + '">delete</a>' + ' ' +
    '<a class="update" href="/lib?method=update&id=' +
    book.id + '&name='+ book.name +
    '&availability='+ book.availability + '">update</a>';
    tr.appendChild(td);
    table.appendChild(tr);
  }

  this.create = create;

};
