import {
    ADD_OTHERQUALIFICATION,
    DELETE_OTHERQUALIFICATION,
    RECEIVE_OTHERQUALIFICATION_LIST
} from 'actions/OtherQualificationActions';

export function otherQualificationList(state = [], action){
    console.log({...state},action)
    switch (action.type) {
        case RECEIVE_OTHERQUALIFICATION_LIST:
            return action.data;
        case ADD_OTHERQUALIFICATION:
            return [
                ...state,action.data];
        case DELETE_OTHERQUALIFICATION:
            let newState = Object.assign([], [...state]);
            console.log("newState", newState);
            newState.filter((item)=>{if(item.id != action.data) return item});
            return newState.filter((item)=>{if(item.id != action.data) return item});
        default:
            return state;
    }
}

