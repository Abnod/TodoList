// var modal;
// var prevValue;
//
// window.addEventListener("click", function (event) {
//     if (event.target === modal) {
//         modal.style.display = "none";
//     }
// });
//
//
// function initForm() {
//     searchButton.addEventListener("click", function (event) {
//         event.preventDefault();
//         searchMedia();
//     });
//     modal = document.getElementById('form');
//     var type = modal.elements["type"];
//     prevValue = '';
//     type.addEventListener("change", function () {
//         var singr = modal.elements["singer"];
//         switch (type.value) {
//             case 'VIDEO':
//                 singr.readOnly = true;
//                 prevValue = singr.value;
//                 singr.value = '';
//                 break;
//             case 'AUDIO':
//                 singr.readOnly = false;
//                 singr.value = prevValue;
//         }
//     });
//     modal.elements.cancel.addEventListener("click", function (event) {
//         event.preventDefault();
//         fillMedia(null);
//         complete(false);
//     });
// }
//
//
// //media edit logic
// function editMedia(media) {
//     formOk.innerHTML = '<input type="submit" class="btn btnInputSize brown" value="Save"/>';
//     prevValue = '';
//     readOnlyAll(false);
//     fillMedia(media);
//
//     modal.onsubmit = function (event) {
//         event.preventDefault();
//         sendReq('media/' + media.id, currentPage);
//     };
//
//     modal.style.display = 'block';
// }
//
//
// //add media logic
// function addMedia() {
//     formOk.innerHTML = '<input type="submit" class="btn btnInputSize brown" value="Add"/>';
//     prevValue = '';
//     readOnlyAll(false);
//     fillMedia(null);
//
//     modal.onsubmit = function (event) {
//         event.preventDefault();
//         sendReq('media/', "last");
//     };
//
//     modal.style.display = 'block';
// }
//
//
// //search media logic
// function searchMedia() {
//     formOk.innerHTML = '<input type="submit" class="btn btnInputSize brown" value="Search"/>';
//     prevValue = '';
//     readOnlyAll(false);
//     fillMedia(null);
//
//     modal.onsubmit = function (event) {
//         event.preventDefault();
//         generatePages(true, 1);
//         complete(true);
//     };
//
//     modal.style.display = 'block';
// }
//
//
// //show media logic
// function showMedia(media) {
//     formOk.innerHTML = '';
//     prevValue = '';
//     fillMedia(media);
//     readOnlyAll(true);
//
//     modal.style.display = 'block';
// }
//
//
// //fills or clear form fields
// function fillMedia(param) {
//     if (param !== null) {
//         modal.elements["singer"].value = param.singer;
//         modal.elements["singer"].readOnly = param.type === "VIDEO";
//         modal.elements["type"].value = param.type;
//         modal.elements["title"].value = param.title;
//         modal.elements["path"].value = param.path;
//     } else {
//         modal.elements["singer"].value = '';
//         modal.elements["type"].value = 'AUDIO';
//         modal.elements["title"].value = '';
//         modal.elements["path"].value = '';
//     }
// }
//
//
// //sends server request
// function sendReq(text, page) {
//     var request = new XMLHttpRequest();
//     var formData = new FormData(modal);
//     request.open("POST", text);
//     request.send(formData);
//     request.onreadystatechange = function () {
//         if (request.readyState !== 4) return;
//         generatePages(false, page);
//         complete(true);
//     }
// }
//
//
// //close modal window
// function complete(bool) {
//     modal.style.display = 'none';
//     document.onkeydown = null;
// }
//
//
// //disable form fields
// function readOnlyAll(bool) {
//     modal.elements["singer"].readOnly = bool;
//     modal.elements["type"].disabled = bool;
//     modal.elements["title"].readOnly = bool;
//     modal.elements["path"].readOnly = bool;
// }
//
//
// //show prompt text for media deletion and send data to server;
// function showCover() {
//     var coverDiv = document.createElement('div');
//     coverDiv.id = 'cover-div';
//     document.body.appendChild(coverDiv);
// }
//
// function hideCover() {
//     document.body.removeChild(document.getElementById('cover-div'));
// }
//
// function showPrompt(media) {
//     showCover();
//     var form = document.getElementById('prompt-form');
//     var container = document.getElementById('prompt-form-container');
//     document.getElementById('prompt-message').innerHTML = 'Are you sure you want to delete record ' + media.title + ' ?';
//
//     form.onsubmit = function (event) {
//         event.preventDefault();
//         var removeRequest = new XMLHttpRequest();
//         removeRequest.open("DELETE", 'media/' + media.id);
//         removeRequest.send();
//         removeRequest.onreadystatechange = function () {
//             if (removeRequest.readyState !== 4) return;
//             generatePages(false, currentPage);
//             complete(true);
//         };
//     };
//
//     form.elements.cancel.onclick = function (event) {
//         event.preventDefault();
//         complete(false);
//     };
//
//     function complete(bool) {
//         hideCover();
//         container.style.display = 'none';
//         document.onkeydown = null;
//     }
//
//     container.style.display = 'block';
// }