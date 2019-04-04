export const RECEIVE_DISTRIBUTOR_LIST = 'RECEIVE_DISTRIBUTOR_LIST';
export const SELECT_DISTRIBUTOR = 'SELECT_DISTRIBUTOR';
export const ADD_DISTRIBUTOR = 'ADD_DISTRIBUTOR';
export const CHANGE_DISTRIBUTOR_PIC = 'CHANGE_DISTRIBUTOR_PIC';
export const ALL_DISTRIBUTOR_OPTIONS = 'ALL_DISTRIBUTOR_OPTIONS';



export function receiveDistributorList(data) {
    return {
        type: RECEIVE_DISTRIBUTOR_LIST,
        data: data
    }
}

export function selectDistributor(data) {
    return {
        type: SELECT_DISTRIBUTOR,
        data: data
    }
}

export function switchScene(data) {
    return {
        type: SWITCH_SCENE,
        data: data
    }
}

export function refreshDistributorList() {
    return dispatch => {
        E.doFind("manager/distributor/query");
        E.addOneTimeEventListener("distributorList", function (e) {
            dispatch(receiveDistributorList(e.data));
        });
    };
}

export function changeDistributorPic(data) {
    return {
        type: CHANGE_DISTRIBUTOR_PIC,
        data: data
    }
}

export function saveDistributor(distributor) {
    return dispatch => {
        E.doAdd("manager/distributor", distributor);

        E.addOneTimeEventListener("distributor", function (e) {

            if(e.data.returnCode == "200") {
                E.dispatchEvent("todistributorlist");
            } else {
                alert("保存失败")
            }
             // dispatch(selectDistributor(e.data));
            // dispatch(refreshdistributorList());
        });
    };
}

export function allDistributorOptions() {
    return dispatch => {
        E.doFind("manager/distributor/options");
        E.addOneTimeEventListener("distributorOptions", function (e) {
            dispatch(receiveDistributorOptions(e.data));
        });
    }
}

export function receiveDistributorOptions(data) {
    return {
        type: ALL_DISTRIBUTOR_OPTIONS,
        data: data
    }
}

export function getDistributor(id) {
    if(id){
        return dispatch => {
            E.doFind("/manager/distributor/"+id);
            E.addOneTimeEventListener("distributor", function (e) {
                if(e.data.returnCode == "200") {
                    const distributor = e.data.result;
                    dispatch(selectDistributor(distributor));
                } else {
                    console.log("查询失败")
                }
            });
        }
    } else {
        return dispatch => {
            const distributor = {name: "", orgCode: "", legalPerson:"", contact:"", phone:"", fax:"", mail:"",province:"",address:"",businessLicense:"",orgCodeCertification:"",taxCertificate:""}
            dispatch(selectDistributor(distributor));
        }
    }
}

export function delDistributor(distributorId, criteria={}, page={}) {
    return dispatch => {
        E.doRemove("manager/distributor/"+ distributorId);
        console.warn(distributorId)
        E.addOneTimeEventListener("distributorDeleted", function (e) {
            dispatch(refreshDistributorList(criteria, page));

        });
    };
}