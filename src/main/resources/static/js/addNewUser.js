'use strict';


let formAddNewUser = document.getElementById('addUserForm');

function createNewUser() {
    let options = formAddNewUser.getElementsByTagName('select')[0].children
    formAddNewUser.addEventListener("submit", ev => {
        ev.preventDefault();
        let roles = [];
        for (let i = 0; i < options.length; i++) {
            if (options[i].selected) roles.push({
                id: options[i].value,
                roleName: "ROLE_" + options[i].text
            });
        }

        fetch("http://localhost:8080/api/admin/users", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: formAddNewUser.querySelector('input#addFirstName').value,
                lastName: formAddNewUser.querySelector('input#addLastName').value,
                age: formAddNewUser.querySelector('input#addAge').value,
                email: formAddNewUser.querySelector('input#addEmail').value,
                password: formAddNewUser.querySelector('input#addPassword').value,
                roles: roles
            })
        }).then(() => {
            formAddNewUser.reset();
            $('#home-tab').click();
            getUserTable()
        });
    });
}
