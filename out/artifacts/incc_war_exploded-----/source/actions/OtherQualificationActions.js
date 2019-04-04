export const RECEIVE_OTHERQUALIFICATION_LIST = 'RECEIVE_OTHERQUALIFICATION_LIST';
export const SELECT_OTHERQUALIFICATION = 'SELECT_OTHERQUALIFICATION';
export const ADD_OTHERQUALIFICATION = 'ADD_OTHERQUALIFICATION';
export const DELETE_OTHERQUALIFICATION = 'DELETE_OTHERQUALIFICATION';
export const CHANGE_OTHERQUALIFICATION_PIC = 'CHANGE_OTHERQUALIFICATION_PIC';


export function receiveOtherQualificationList(data) {
    return {
        type: RECEIVE_OTHERQUALIFICATION_LIST,
        data: data
    }
}

export function selectOtherQualification(data) {
    return {
        type: SELECT_OTHERQUALIFICATION,
        data: data
    }
}

export function refreshOtherQualificationList() {
    return dispatch => {
        E.doFind("manager/otherqualification");
        E.addOneTimeEventListener("otherQualificationList", function (e) {
            dispatch(receiveotherQualificationList(e.data));
        });
    };
}

export function changeOtherQualificationPic(data) {
    return {
        type: CHANGE_OTHERQUALIFICATION_PIC,
        data: data
    }
}

export function addOtherQualification(otherQualification) {
    return dispatch => {
        console.log(otherQualification);
        E.doAdd("manager/otherqualification", otherQualification);

        E.addOneTimeEventListener("otherQualification", function (e) {
             // dispatch(selectOtherQualification(e.data));
            // dispatch(refreshotherQualificationList());
            dispatch(addOtherQualification2List(e.data));
        });
    };
}
export function addOtherQualification2List(data) {
    return {
        type: ADD_OTHERQUALIFICATION,
        data: data.result
    }
}
export function delOtherQualification(otherQualificationId) {
    return dispatch => {
        console.log(otherQualificationId);
        E.doRemove("manager/otherqualification/"+ otherQualificationId);

        E.addOneTimeEventListener("otherQualificationDeleted", function (e) {
             // dispatch(selectOtherQualification(e.data));
            // dispatch(refreshotherQualificationList());
            dispatch(delFromList(e.data))
        });
    };
}

export function delFromList(data){
    return {
        type: DELETE_OTHERQUALIFICATION,
        data: data.result
    }
}
