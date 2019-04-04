export const RECEIVE_PERSON_LIST = 'RECEIVE_PERSON_LIST';
export const SELECT_PERSON = 'SELECT_PERSON';
export const MODIFY_PERSON = 'MODIFY_PERSON';
export const ADD_PERSON = 'ADD_PERSON';
export const SWITCH_SCENE = 'SWITCH_SCENE';
export const CHANGE_PERSON_PIC = 'CHANGE_PERSON_PIC';

export const SCENE_BLANK = 'SCENE_BLANK';
export const SCENE_NEW = 'SCENE_NEW';
export const SCENE_EDIT = 'SCENE_EDIT';
export const SCENE_VIEW = 'SCENE_VIEW';

export function receivePersonList(data) {
    return {
        type: RECEIVE_PERSON_LIST,
        data: data
    }
}

export function selectPerson(data) {
    return {
        type: SELECT_PERSON,
        data: data
    }
}

export function switchScene(data) {
    return {
        type: SWITCH_SCENE,
        data: data
    }
}

export function refreshPersonList() {
    return dispatch => {
        E.doFind("person");
        E.addOneTimeEventListener("personList", function (e) {
            dispatch(receivePersonList(e.data));
        });
    };
}

export function changePersonPic(data) {
    return {
        type: CHANGE_PERSON_PIC,
        data: data
    }
}

export function savePerson(person) {
    return dispatch => {

        if (person.id)
            E.doModify("person", person);
        else
            E.doAdd("person", person);

        E.addOneTimeEventListener("person", function (e) {
            dispatch(selectPerson(e.data));
            dispatch(refreshPersonList());
        });
    };
}
