
import {
    RECEIVE_AUDIT_LIST,

} from 'actions/AuditActions';

export function  auditList(state = [], action){
    switch(action.type){
        case RECEIVE_AUDIT_LIST:
            return action.data;
        default:
            return state;
    }
}

