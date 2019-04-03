       
import {
    RECEIVE_USER_LIST,
    USER_STATUS_CHANGED,
    USER_CRITERIA_CHANGED,
    SELECT_USER,
    CURRENT_USER
} from 'actions/UserActions';

export function  userList(state = [], action){
    switch(action.type){
        case RECEIVE_USER_LIST:
            return action.data;
        default:
            return state;
    }
}

export function  userCriteria(state = [], action){
    switch(action.type){
        case USER_CRITERIA_CHANGED:
            return action.data;
        default:
            return state;
    }
}

export function  userStatus(state = [], action){
    switch(action.type){
        case USER_STATUS_CHANGED:
            return action.data;
        default:
            return state;
    }
}

export function selectedUser(state = {id:"", name: "", idCard: "", mail:"", phone:"", distributorId:""}, action) {
    switch (action.type){
        case SELECT_USER:
            return action.data;
        default:
            return state;
    }
}

export function currentUser(state = {}, action) {
    console.log(action)
    switch (action.type){
        case CURRENT_USER:
            return action.data;
        default:
            return state;
    }
}