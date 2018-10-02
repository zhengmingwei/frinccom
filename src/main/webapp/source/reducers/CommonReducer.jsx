import {
    SHOW_MODAL_DIALOG
} from 'actions/CommonAction.jsx';

export function  modalDialog(state = [], action){
    switch(action.type){
        case SHOW_MODAL_DIALOG:
            return action.data;
        default:
            return state;
    }
}