let insertButton = document.getElementById('insert-new-book');
insertButton.onclick = function() {
  let xhr = new XMLHttpRequest();
  let name = prompt('Enter name');
  let date = prompt('Enter date (yyyy-mm-dd)');
  let availability = prompt('Enter availability (true, false)');
  let parameter ='&name=' + name +
     '&date=' + date + '&availability=' + availability;
  xhr.open('GET', '/lib?method=insert' + parameter, false);
  xhr.send();

  if (xhr.status != 200) {
    alert( xhr.status + ': ' + xhr.statusText );
  } else {
    let json = xhr.responseText;
    let library = JSON.parse(json);
    workWithTable(library);false
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
    let library = JSON.parse(json);
    workWithTable(library);
  }
};

// let input = document.body.getElementsByTagName('input');
// input.onchange = function() {
//
// }

document.body.onclick = function(event) {
  if (event.target.nodeName != 'A') return;

  if (event.target.classList.contains("update")) {
    let tr = event.target.parentNode.parentNode;
    let id = tr.className;
    let name = document.getElementById('name_' + id).value;
    let date = document.getElementById('date_' + id).value;
    let availability = document.getElementById('availability_' + id).value;
    event.target.href ='/lib?method=update&id=' + id +'&name=' + name +
       '&date=' + date + '&availability=' + availability;
  }

  let href = event.target.getAttribute('href');
  let xhr = new XMLHttpRequest();

  xhr.open('GET', href, false);
  xhr.send();

  if (xhr.status != 200) {
    alert( xhr.status + ': ' + xhr.statusText );
  } else {
    let json = xhr.responseText;
    let library = JSON.parse(json);
    workWithTable(library);
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
        let date = book[key].year + '-' + book[key].month + '-' + book[key].day;
        let id = key + '_' + book.id;
        td.innerHTML ='<input type="text" value="' + date +'" id="' +
        id + '">';
        td.classList.add("date");
        tr.appendChild(td);
      } else {
        let td = document.createElement('td');
        let id = key + '_' + book.id;
        key == "id" ? td.innerHTML = book[key] : td.innerHTML = '<input type="text" id="' +
        id + '" value="' + book[key] + '">';
        tr.appendChild(td);
      }
    }

    let td = document.createElement('td');
    td.innerHTML = '<a class="delete" href="/lib?method=delete&id=' +
       book.id + '">delete</a>' + ' ' +
       '<a class="update" href="/">update</a>';

    tr.appendChild(td);
    tr.classList.add(book.id);
    table.appendChild(tr);
  }

  this.create = create;
};
