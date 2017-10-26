let insertButton = document.getElementById('insert-new-book');
insertButton.onclick = function() {
  let xhr = new XMLHttpRequest();
  xhr.open('GET', '/test?id=1', false);
  xhr.send();
  if (xhr.status != 200) {
    alert( xhr.status + ': ' + xhr.statusText );
  } else {
    alert( xhr.responseText );
  }
};

let viewAllButton = document.getElementById('view-all-book');
viewAllButton.onclick = function() {
  alert(viewAllButton.innerHTML);
};
