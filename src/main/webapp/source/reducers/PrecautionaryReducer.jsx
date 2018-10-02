
import {
    RECEIVE_PRECAUTIONARY_LIST,
    PRECAUTIONARY_CRITERIA
} from 'actions/PrecautionaryActions';

export function  precautionaryList(state = [], action){
    switch(action.type){
        case RECEIVE_PRECAUTIONARY_LIST:
            return action.data;
        default:
            return state;
    }
}

export function  precautionaryCriteria(state = [], action){
    switch(action.type){
        case PRECAUTIONARY_CRITERIA:
            return action.data;
        default:
            return state;
    }
}
