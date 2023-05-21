"use strict"
const host = "http://localhost:8080/api/admin/users/"
let user = undefined

async function getUser() {
    const data = await $.get('/api/users')
    user = data
    user.roles  = user.roles;
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