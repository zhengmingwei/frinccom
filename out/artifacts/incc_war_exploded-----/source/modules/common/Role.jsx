import React from 'react';
import ReactDOM from 'react-dom';
/**
 * User: zhyj
 * Date: 2017/7/31.
 * Time: 17:18.
 */
export default class Role extends React.Component {
    constructor() {
        super(...arguments);
    }

    render() {
        const {requireRoles=[]} = this.props;
        const {hasRoles=[]} = this.props;
        const showMenu = requireRoles.find((n) => hasRoles.includes(n))
        return (
            <div>
                {showMenu?this.props.children:""}
            </div>
        )
    }
};