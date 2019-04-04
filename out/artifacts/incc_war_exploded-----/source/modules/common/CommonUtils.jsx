/**
 * User: zhyj
 * Date: 2017/8/18.
 * Time: 09:10.
 */

export function objToStrMap  (obj) {
    let strMap = new Map();
    for (let k of Object.keys(obj)) {
        strMap.set(k, obj[k]);
    }
    return strMap;
};

export function errorMessages (err){
    let errstr = '';
    for (let k of Object.keys(err)) {
        if(err[k].errors) {

            let errors = err[k].errors;
            for (let l in errors) {
                errstr += errors[l].message + '\n';
            }
        } else {
            errstr += errorMessages(err[k]);
        }
    }
    return errstr;
}