
import {
    RECEIVE_DISTRIBUTOR_LIST,
    DISTRIBUTOR_STATUS_CHANGED,
    DISTRIBUTOR_CRITERIA_CHANGED,
    ALL_DISTRIBUTOR_OPTIONS,
    SELECT_DISTRIBUTOR
} from 'actions/DistributorActions';

export function  distributorList(state = [], action){
    switch(action.type){
        case RECEIVE_DISTRIBUTOR_LIST:
            return action.data;
        default:
            return state;
    }
}

export function  distributorCriteria(state = [], action){
    switch(action.type){
        case DISTRIBUTOR_CRITERIA_CHANGED:
            return action.data;
        default:
            return state;
    }
}

export function  distributorStatus(state = [], action){
    switch(action.type){
        case DISTRIBUTOR_STATUS_CHANGED:
            return action.data;
        default:
            return state;
    }
}

export function  distributorOptions(state = [], action){
    switch(action.type){
        case ALL_DISTRIBUTOR_OPTIONS:
            return action.data;
        default:
            return state;
    }
}

export function selectedDistributor(state = {name: "", orgCode: "", legalPerson:"", contact:"", phone:"", fax:"", mail:"",province:"",address:"",businessLicense:"",orgCodeCertification:"",taxCertificate:""}, action) {
    switch (action.type){
        case SELECT_DISTRIBUTOR:
            return action.data;
        default:
            return state;
    }
}