// var json;
// var roleAdmin;
// var currentPage;
// var lastPage;
//
// document.onreadystatechange = function () {
//     if (document.readyState === 'complete') {
//         var checkRights = new XMLHttpRequest();
//         checkRights.open("POST", "media/");
//         checkRights.send();
//         checkRights.onreadystatechange = function () {
//             if (checkRights.readyState !== 4) return;
//             roleAdmin = checkRights.status !== 403;
//             initForm();
//             currentPage = 1;
//             generatePages(false, 1);
//         };
//     }
// };
//
// function getMedia(booleanSearch, page) {
//     var xhr = new XMLHttpRequest();
//     if (booleanSearch) {
//         tableHeader.innerHTML = 'Search Results';
//         addAllButton();
//         var formData = new FormData(modal);
//         xhr.open("POST", "media/search/" + page);
//         xhr.send(formData);
//     } else {
//         tableHeader.innerHTML = 'List of Media';
//         backToAll.innerHTML = '';
//         xhr.open("GET", "media/" + page);
//         xhr.send();
//     }
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState !== 4) return;
//         if (xhr.status !== 200) {
//             // обработать ошибку
//             alert(xhr.status + ': ' + xhr.statusText);
//         } else {
//             try {
//                 json = JSON.parse(xhr.responseText);
//             } catch (e) {
//                 alert("Некорректный ответ " + e.message);
//             }
//             showAll(json);
//         }
//     };
// }
//
// function showAll(data) {
//     data.forEach(function (media) {
//         var div = media_list.appendChild(document.createElement('tr'));
//         div.setAttribute("class", "media_row");
//         var title = div.appendChild(document.createElement('td'));
//         title.setAttribute("class", "media_row_cell");
//         title.innerHTML = media.title;
//         var type = div.appendChild(document.createElement('td'));
//         type.setAttribute("class", "media_row_cell");
//         type.innerHTML = media.type;
//         var show = div.appendChild(document.createElement('td'));
//         show.setAttribute("class", "table_button blue");
//         show.innerHTML = "Show info";
//         show.addEventListener("click", function () {
//             showMedia(media);
//         });
//         if (roleAdmin) {
//             var edit = div.appendChild(document.createElement('td'));
//             edit.setAttribute("class", "table_button grey");
//             edit.innerHTML = "Edit";
//             edit.addEventListener("click", function () {
//                 editMedia(media);
//             });
//
//             var remove = div.appendChild(document.createElement('td'));
//             remove.setAttribute("class", "table_button red");
//             remove.innerHTML = "Delete";
//             remove.addEventListener("click", function () {
//                 showPrompt(media);
//             });
//         }
//     });
//
//     if (roleAdmin) {
//         var adb = document.getElementById("adb");
//         adb.innerHTML = '<a href="" id="addLink" class="btn btnDivSize brown">Add new record</a>';
//         adb.addEventListener("click", function (event) {
//             event.preventDefault();
//             addMedia();
//         });
//     }
// }
//
// function clearTableContent() {
//     var rowsForRemoval = media_list.getElementsByClassName("media_row");
//     while (rowsForRemoval[0]) rowsForRemoval[0].parentNode.removeChild(rowsForRemoval[0]);
// }
//
// function generatePages(booleanForSearch, page) {
//     clearTableContent();
//     var xhr = new XMLHttpRequest();
//     if (booleanForSearch) {
//         var formData = new FormData(modal);
//         xhr.open("POST", "media/searchpages/");
//         xhr.send(formData);
//     } else {
//         xhr.open("GET", "media/pages/");
//         xhr.send();
//     }
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState !== 4) return;
//         var pageCount = xhr.responseText;
//         lastPage = pageCount;
//
//         if (page === "last") {
//             page = lastPage;
//         } else if (page > lastPage) {
//             page = lastPage;
//         }
//         currentPage = page;
//
//         var pagingBlock = document.getElementById("pagination");
//         pagingBlock.innerHTML = '';
//         for (i = 1; i <= pageCount; i++) {
//             var temp = document.createElement('a');
//             pagingBlock.appendChild(temp);
//             temp.innerHTML = i;
//             temp.setAttribute("class", "btn btnDivSize brown mar10");
//             temp.setAttribute("href", "");
//             temp.setAttribute("name", i);
//             temp.addEventListener("click", function (event) {
//                 event.preventDefault();
//                 currentPage = event.target.getAttribute("name");
//                 if (booleanForSearch) {
//                     generatePages(true, currentPage);
//                 } else {
//                     generatePages(false, currentPage);
//                 }
//             });
//         }
//         getMedia(booleanForSearch, page)
//     };
// }
//
// function addAllButton() {
//     backToAll.innerHTML = '<a href="" id="AllButton" class="btn btnDivSize blue">Show All Entries</a>';
//     backToAll.addEventListener("click", function (event) {
//         event.preventDefault();
//         generatePages(false, 1)
//     });
// }