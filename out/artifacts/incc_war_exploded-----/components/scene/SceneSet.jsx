import React from 'react';
import ReactDOM from 'react-dom';
import EventReactComponent from '../event/EventReactComponent';
/**
 * User: zyj
 * Date: 16/5/24.
 * Time: 下午2:34.
 */
export default class SceneSet extends EventReactComponent {
    constructor(props) {
        super(props);
        this.state = {
            active: null,
            data:this.props.data
        }
    }

    getActive() {
        return (this.state.active||this.props.active);
    }

    switchTo(name) {
        this.setState({active: name});
    }

    refresh() {
        this.setState({active: this.props.active});
        console.log("refresh");
    }

    render() {
        console.log("-----切换至-----", this.getActive());
        let children = [];
        this.props.children.forEach((child) => {
            if (child.props.static === true || child.props.name === this.getActive()) {
                children.push(child);
            }
        });
        //if (current.props.children.map) {
        //    children = current.props.children.map((child, i) => {
        //        return React.cloneElement(child, {key: i, data:this.props.data});
        //    });
        //} else if(typeof current.props.children === 'object') {
        //    children = React.cloneElement(current.props.children, {key: i, data:this.props.data});
        //} else {
        //    children = current.props.children;
        //}
        return (
            <div data-name="set">
                {children}
            </div>
        )
    }
};