alert("Hello ");

let insertButton = document.getElementById('insert-new-book');
insertButton.onclick = function() {
    alert(insertButton.innerHTML);
};

let viewAllButton = document.getElementById('view-all-book');
viewAllButton.onclick = function() {
  alert(viewAllButton.innerHTML);
};
