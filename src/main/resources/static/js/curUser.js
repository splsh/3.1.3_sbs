// "use strict"
// const host = "http://localhost:8080/api/admin/users/"
// let userDTO = undefined
//
// async function getUser() {
//     const data = await $.get('/api/users')
//     userDTO = data
//     // userDTO.roles  = userDTO.roles.map(role => role.replace("ROLE_", ""));
// }
//
// async function getUserById(id) {
//     let response = await fetch(host + id);
//     if (response.ok) {
//         return await response.json();
//     } else {
//         alert(`Error, ${response.status}`)
//     }
// }
//
// async function getAllUsers() {
//     let response = await fetch(host);
//     if (response.ok) {
//         return await response.json();
//     } else {
//         alert(`Error, ${response.status}`)
//     }
// }
//
// function setUserDataInPage() {
//     let usersBody =
//         "<td>" + userDTO.id + "</td>" +
//         "<td>" + userDTO.firstName + "</td>" +
//         "<td>" + userDTO.lastName + "</td>" +
//         "<td>" + userDTO.age + "</td>" +
//         "<td>" + userDTO.email + "</td>" +
//         // "<td>" + userDTO.roles + "</td>"
//
//     $("#user").html(usersBody);
//     $("#authUserEmail").html(userDTO.email);
//     // $("#authUserRoles").html(userDTO.roles);
// }
//
// $(document).ready(function () {
//     getUser().then(setUserDataInPage)
// })

"use strict";

const host = "http://localhost:8080/api/admin/users/";
let user = undefined;

async function getUser() {
    const data = await $.get('/api/users');
    user = new UserDTO(data);
    user.roles = user.roles.map(role => role.replace("ROLE_", ""));
}

async function getUserById(id) {
    let response = await fetch(host + id);
    if (response.ok) {
        return await response.json();
    } else {
        alert(`Error, ${response.status}`)
    }
}

async function getAllUsers() {
    let response = await fetch(host);
    if (response.ok) {
        return await response.json();
    } else {
        alert(`Error, ${response.status}`)
    }
}

function setUserDataInPage() {
    let usersBody =
        "<td>" + user.id + "</td>" +
        "<td>" + user.firstName + "</td>" +
        "<td>" + user.lastName + "</td>" +
        "<td>" + user.age + "</td>" +
        "<td>" + user.email + "</td>" +
        "<td>" + user.roles + "</td>"

    $("#user").html(usersBody);
    $("#authUserEmail").html(user.email);
    $("#authUserRoles").html(user.roles);
}

$(document).ready(function () {
    getUser().then(setUserDataInPage)
})