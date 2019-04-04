import React from 'react';
import ReactDOM from 'react-dom';
import EventReactComponent from '../event/EventReactComponent';
/**
 * User: zyj
 * Date: 16/5/24.
 * Time: 下午2:34.
 */
export default class Scene extends EventReactComponent {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <div data-name="scene">
                {this.props.children}
            </div>
        )
    }
};