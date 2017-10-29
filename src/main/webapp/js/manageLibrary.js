let insertButton = document.getElementById('insert-new-book');
insertButton.onclick = function() {
  let xhr = new XMLHttpRequest();
  xhr.open('GET', '/test?method=insert', false);
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
  xhr.open('GET', '/test?method=viewall', false);
  xhr.send();
  if (xhr.status != 200) {
    alert( xhr.status + ': ' + xhr.statusText );
  } else {
    // alert( xhr.responseText );
    let books = JSON.parse(xht.responseText);
    for ( var key in books ) {
      alert( "Ключ: " + key + " значение: " + books[key] );
    }
  }
};
