/**
 * Created by admin on 2016/6/7.
 */

function isDel(i) {
    if(confirm("是否删除该类型")){
        window.location.href="/category/CategoryMnt_delete.do?id=" + i;
    }
}
// annoutnce
function isDel_announce(i) {
    if(confirm("是否删除该类型")){
        window.location.href = "/announce/AnnounceMnt_delete.do?id=" + i;
    }
}

//announce
function edit(id) {
    window.location.href="/medical/MedicalMnt_toedit.do?id=" + i;
}


//是否删除
function isDel_m(i) {
    if(confirm("是否删除该药")){
        window.location.href="/medical/MedicalMnt_delete.do?id=" + i;
    }
}


function mbar(sobj) {
    var val =sobj.options[sobj.selectedIndex].value;
    if (val != "") {
        window.location.href="/medical/LoadMedical.do?categoryId=" + val + "&currPage=1";
        // sobj.selectedIndex=0;
        // sobj.blur();
    }
}

function search() {
    var val = document.getElementById("input-search").value;   //获取text的值
    if(val == null|| val === ""){
    }
    else {
        var str = "/medical/SearchMedical.do?currPage=1&name="+val;
        window.location.href=str;
    }
}

