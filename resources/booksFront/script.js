const endpoint = 'http://localhost:8080/bookApplication/all',
    availableCategory = 'http://localhost:8080/bookApplication/availableCategory';
var books;

const contentContainer = document.getElementsByClassName('content-container')[0];

function createBooksElement(booksArray){

    var html = "";
    page = document.createElement('div');

    if (booksArray.totalItems == 0) {
        html = `<h1>No elements.</h1>`;
        page.className += "error";
        document.getElementById("search").value = "";

    } else {
        html = booksArray.map(function (book) {

            const title = book.volumeInfo.title;
            const cover = book.volumeInfo.imageLinks === undefined ? "defbookcover.jpg" : book.volumeInfo.imageLinks.thumbnail;

            return `<article>
                        <img src="${cover}" alt="image">
                        <h1>${title}</h1>
                                
                   </article>`
        }).join('');

        page.className += "flex-container";
    }

    page.innerHTML = html;

    return page;
}

function loadBooks(method, url){

    return new Promise(function (resolve, reject) {
        var httpRequest  = new XMLHttpRequest();
        httpRequest .open(method, url);

        httpRequest.onload = function () {
            if (this.status >= 200 && this.status < 300) {
                resolve(httpRequest .response);
                books = JSON.parse(httpRequest.response);

                contentContainer.appendChild(createBooksElement(books.items));


            } else {
                reject({
                    status: this.status,
                    statusText: httpRequest .statusText
                });
            }
        };
        httpRequest .onerror = function () {
            error = document.createElement('h3');
            error.className += "error";
            error.innerText = 'The request failed!';
            contentContainer.appendChild(error);

            reject({
                status: this.status,
                statusText: httpRequest .statusText
            });
        };
        httpRequest.send();
    });

}


function getAvailableCategory(method, url){

    return new Promise(function (resolve, reject) {
        var httpRequest  = new XMLHttpRequest();
        httpRequest .open(method, url);

        httpRequest.onload = function () {
            if (this.status >= 200 && this.status < 300) {
                resolve(httpRequest .response);

                var categories = JSON.parse(httpRequest.response);

                var select = document.getElementById("categorySelect");
                for(index in categories) {
                    select.options[select.options.length] = new Option(categories[index], categories[index]);
                }

            }
        };

        httpRequest .onerror = function () {
            error = document.createElement('h3');
            error.className += "error";
            error.innerText = 'The request failed!';
            contentContainer.appendChild(error);

            reject({
                status: this.status,
                statusText: httpRequest .statusText
            });
        };
        httpRequest.send();
    });

}


function displayBook() {
    getAvailableCategory('GET', availableCategory)
    .then(
        loadBooks('GET', endpoint)
    ).catch(function (err) {
        console.error('Augh, there was an error!', err.statusText);
    });
}

displayBook();




$('#categorySelect').change(function() {
    var booksByCategory = [];

    if($(this).val() !== "all"){
        books.items.forEach( book =>{
            if( null != book.volumeInfo.categories){
                if($.inArray($(this).val(), book.volumeInfo.categories) === 0)
                    booksByCategory.push(book);
            }
        });
    }else{
        booksByCategory = books.items;
    }

    while (contentContainer.firstChild) {
        contentContainer.removeChild(contentContainer.firstChild);
    }

    setTimeout(function(){
        contentContainer.appendChild(createBooksElement(booksByCategory));
    }, 500);

});

