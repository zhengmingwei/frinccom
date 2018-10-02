import {
    ADD_SPECIALITEM,
    DELETE_SPECIALITEM,
    RECEIVE_SPECIALITEM_LIST
} from 'actions/SpecialItemActions';

export function specialItemList(state = [], action){
    switch (action.type) {
        case RECEIVE_SPECIALITEM_LIST:
            return action.data;
        case ADD_SPECIALITEM:
            return [
                ...state,action.data];
        case DELETE_SPECIALITEM:
            let newState = Object.assign([], [...state]);
            console.log("newState", newState);
            newState.filter((item)=>{if(item.id != action.data) return item});
            return newState.filter((item)=>{if(item.id != action.data) return item});
        default:
            return state;
    }
}

