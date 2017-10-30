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
    workWothTable(library);
  }
};


function workWothTable(library) {
  let table = new Table(library);
  let oldTable = document.getElementById('library');
  if (!oldTable) {
    alert ('add');
    document.body.appendChild(table.create());
  } else {
    alert ('replace');
    document.body.replaceChild(table.create(), oldTable);
  }
}

function Table(library) {
  var table = document.createElement('table');

  function create() {
    for (let key in library) {
      renderRow (library[key]);
    }
    table.id = 'library';
    return table;
  }

  function renderRow(book){
    let tr = document.createElement('tr');
    for (let key in book) {
      let td = document.createElement('td');
      td.innerHTML = book[key];
      td.classList.add(key);
      tr.appendChild(td);
    }
    table.appendChild(tr);
  }

  this.create = create;

};
