export const SHOW_MODAL_DIALOG = 'SHOW_MODAL_DIALOG';


export function showModalDialog(data) {
    return {
        type: SHOW_MODAL_DIALOG,
        data: data
    }
}
