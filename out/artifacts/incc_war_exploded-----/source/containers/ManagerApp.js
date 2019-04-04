import React, {Component} from "react";

import SideMenu from "modules/main/SideMenu";
import Header from "modules/common/Header";
import {connect} from 'react-redux';
import {getCurrentUser} from 'actions/UserActions';
import "antd/dist/antd.css";

import Dialog from "modules/common/Dialog";


class App extends Component {
    constructor(props) {
        super(props);
    }

    componentWillMount(){
        this.props.dispatch(getCurrentUser());
        console.log("context",this.context)
    }
    componentDidMount() {
        //this.props.dispatch(refreshPersonList());
    }

    render() {
        console.log("*", this.props);
        return (
            <div className="ant-layout">
                <Header {...this.props}/>
                <div className="ant-layout-aside">
                    <aside className="ant-layout-sider" style={{backgroundColor: "#ececec"}}>
                        <SideMenu {...this.props}/>
                    </aside>
                    <div className="ant-layout-main">
                        {this.props.children}
                        {/*<div className="ant-layout-footer">
                         可可易游 版权所有 © 2016 可可易游技术中心
                         </div>*/}
                    </div>
                    <Dialog/>
                </div>
            </div>
        )
    }
}
export default connect(
    state => {
        let {currentUser} = state;
        return {
            currentUser
        }
    }
)(App);