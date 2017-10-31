let insertButton = document.getElementById('insert-new-book');
insertButton.onclick = function() {
  let xhr = new XMLHttpRequest();
  let name = prompt('Enter name');
  let date = prompt('Enter date (yyyy-mm-dd)');
  let availability = prompt('Enter availability (true, false)');
  let parametr ='&name=' + name +
     '&date=' + date + '&availability=' + availability;
  xhr.open('GET', '/lib?method=insert' + parametr, false);
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

// let table = document.body.getElementById('library');
// table.oninput = function(event) {
//   if (event.target.nodeName != 'INPUT') return;
//   event.target.value =
// }

document.body.onclick = function(event) {
  if (event.target.nodeName != 'A') return;

  if (event.target.classList.contains("update")) {
    let tr = event.target.parentNode.parentNode;
    let id = tr.className;
    let name = tr.second.value;
    alert(tr.children[1].innerHTML);
    let date = tr.children[2].value;
    alert(tr.children[2].innerHTML);
    alert(date);
    let availability = tr.children[3].value;
    alert(tr.children[3].innerHTML);
    alert (availability);
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
    alert(json);
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
        let date = book[key].year+ '-' + book[key].month + '-' + book[key].day;
        td.innerHTML = '<input type="text" value=' + date + '>';
        td.classList.add("date");
        tr.appendChild(td);
      } else {
        let td = document.createElement('td');
        key == "id" ? td.innerHTML = book[key] : td.innerHTML = '<input type="text" value=' +
        book[key] + '>';
        td.classList.add(key);
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
