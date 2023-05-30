function subscribe(myId, idSubscribe) {
    $.ajax({
        url: "/user/add/subscribe",
        type: "GET",
        data: {
            myId: myId,
            idSubscribe: idSubscribe
        },
        success: function (response) {
            document.getElementById('btn_subscribe').hidden = true;
            document.getElementById('btn_unsubscribe').hidden = false;
            alert("DONE");
        }
    });
}

function unsubscribe(myId, idSubscribe) {
    $.ajax({
        url: "/user/add/unsubscribe",
        type: "GET",
        data: {
            myId: myId,
            idSubscribe: idSubscribe
        },
        success: function (response) {
            document.getElementById('btn_subscribe').hidden = false;
            document.getElementById('btn_unsubscribe').hidden = true;
            alert("DONE");
        }
    });
}
