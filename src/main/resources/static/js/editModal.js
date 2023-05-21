const id_edit = document.getElementById('idEdit')
const firstName_edit = document.getElementById('firstNameEdit')
const lastName_edit = document.getElementById('lastNameEdit')
const age_edit = document.getElementById('ageEdit')
const email_edit = document.getElementById('emailEdit')

function fillEditModalTable(id) {
    $('editModal').modal('show')
    getUserById(id).then(user => {
        id_edit.value = `${user.id}`;
        firstName_edit.value = `${user.firstName}`;
        lastName_edit.value = `${user.lastName}`;
        age_edit.value = `${user.age}`;
        email_edit.value = `${user.email}`;
    })
}

const formEdit = document.getElementById('formEdit');
editUser();

function editUser() {
    let optionsEdit = formEdit.getElementsByTagName('select')[0].children
    formEdit.addEventListener("submit", ev => {
        ev.preventDefault();
        let roles = [];
        for (let i = 0; i < optionsEdit.length; i++) {
            if (optionsEdit[i].selected) roles.push({
                id: optionsEdit[i].value,
                roleName: "ROLE_" + optionsEdit[i].text
            });
        }

        fetch("http://localhost:8080/api/admin/users/", {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formEdit.querySelector('input#idEdit').value,
                firstName: formEdit.querySelector('input#firstNameEdit').value,
                lastName: formEdit.querySelector('input#lastNameEdit').value,
                age: formEdit.querySelector('input#ageEdit').value,
                email: formEdit.querySelector('input#emailEdit').value,
                password: formEdit.querySelector('input#passwordEdit').value,
                roles: roles
            })
        }).then(() => {
            $('#closeEdit').click();
            getUserTable();
        });
    });
}