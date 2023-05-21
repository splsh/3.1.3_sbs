const id_delete = document.getElementById('deleteId')
const firstName_delete = document.getElementById('deleteFirstName')
const lastName_delete = document.getElementById('deleteLastName')
const age_delete = document.getElementById('deleteAge')
const email_delete = document.getElementById('deleteEmail')
// let role_delete = document.getElementById('deleteUserRoles')
const deleteForm = document.getElementById('formDeleteUser');

function clearDeleteModalTable() {
    id_delete.value = ''
    firstName_delete.value = ''
    lastName_delete.value = ''
    age_delete.value = ''
    email_delete.value = ''
    // $(role_delete).find('option').remove()
}

function fillDeleteModalTable(id) {
    clearDeleteModalTable()
    $('deleteModal').modal('show');
    getUserById(id).then(user => {
        id_delete.value = `${userDTO.id}`;
        firstName_delete.value = `${userDTO.firstName}`;
        lastName_delete.value = `${userDTO.lastName}`;
        age_delete.value = `${userDTO.age}`;
        email_delete.value = `${userDTO.username}`;
        // user.roles.forEach(role => role_delete.add(new Option(role.roleName.replace("ROLE_", ""))))

    })
}
// <td id="userId">${userDTO.id}</td>
// <td>${userDTO.firstName}</td>
// <td>${userDTO.lastName}</td>
// <td>${userDTO.age}</td>
// <td>${userDTO.email}</td>
// <td>${userDTO.roles}</td>

function deleteUser() {
    deleteForm.addEventListener("submit", ev => {
        ev.preventDefault();
        let id = id_delete.value
        fetch("http://localhost:8080/api/admin/users/" + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            $('#buttonCloseDelete').click();
            getUserTable();
        })
    });
}