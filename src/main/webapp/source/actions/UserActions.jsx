
import Network from 'tigerfacejs-network';

export const RECEIVE_USER_LIST = 'RECEIVE_USER_LIST';
export const USER_STATUS_CHANGED = 'USER_STATUS_CHANGED';
export const USER_CRITERIA_CHANGED = 'USER_CRITERIA_CHANGED';
export const SELECT_USER = 'SELECT_USER';
export const CURRENT_USER = 'CURRENT_USER';

export function receiveUserList(data) {
    return {
        type: RECEIVE_USER_LIST,
        data: data
    }
}

export function selectUser(data) {
    return {
        type: SELECT_USER,
        data: data
    }
}

export function refreshUserList(criteria={}, page={}) {
    return dispatch => {
        E.doFind("manager/user/query",Object.assign({}, criteria, page));
        E.addOneTimeEventListener("userList", function (e) {
            dispatch(receiveUserList(e.data));
        });
    };
};

export function userCriteriaChanged(data) {
    return {
        type: USER_CRITERIA_CHANGED,
        data: data
    }
};

export function changeUserStatus(data) {
    let userInfo = {};
    return dispatch => {
        Object.assign(userInfo, data);
        E.doModify("manager/user/changeUserStatus", userInfo.user);
        E.addOneTimeEventListener("userStatusChanged", function (e) {
            dispatch(userStatusChanged(userInfo));
        });
    }
}
export  function userStatusChanged(data){
    return {
        type : USER_STATUS_CHANGED,
        data :data
    }
}


export function saveUser(user) {
    return dispatch => {
        E.doAdd("manager/user", user);

        E.addOneTimeEventListener("user", function (e) {
            if(e.data.returnCode == "200"){
                E.dispatchEvent("touserlist");
            } else {
                alert("保存失败");// alert(e.data.message);
            }
            // dispatch(selectDistributor(e.data));
            // dispatch(refreshcommodityList());
        });
    };
}

export function getUser(id) {
    if(id){
        return dispatch => {
            E.doFind("manager/user/"+id);
            E.addOneTimeEventListener("user", function (e) {
                if(e.data.returnCode == "200") {
                    const use = e.data.result;
                    dispatch(selectUser(use));
                } else {
                    console.log("查询失败")
                }
            });
        }
    } else {
        return dispatch => {
            const use = {name: "", idCard: "", mail:"", phone:"", distributorId:""}
            dispatch(selectUser(use));
        }
    }
}

export function delUser(useId, criteria={}, page={}) {
    return dispatch => {
        E.doRemove("manager/user/"+ useId);
        E.addOneTimeEventListener("useDeleted", function (e) {
            dispatch(refreshUseList(criteria, page));

        });
    };
}

export function receiveCurrentUser(data){
    return  {
        type: CURRENT_USER,
        data: data
    }
}

export function getCurrentUser(){
    return dispatch => {
        E.doFind("security/user");
        E.addOneTimeEventListener("currentuser", function (e) {
            if(e.data.returnCode == "200") {
                console.log(e)
                const user = e.data.result;
                dispatch(receiveCurrentUser(user));
            } else {
                console.log("查询失败")
            }
        });
    };
}

export function changePwd(password, oldpassword){
    return dispatch => {
        E.doAdd("manager/user/resetPassword?password="+password+"&oldpassword="+oldpassword);
        E.addOneTimeEventListener("resetpwd", function (e) {
            if(e.data.returnCode == "200") {
                alert("修改成功！")
            } else {
                alert("修改失败")
            }
        });
    };
}