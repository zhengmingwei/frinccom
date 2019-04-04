import React from 'react';
import ReactDOM from 'react-dom';

import { Breadcrumb } from 'antd';
/**
 * User: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
export default class Welcome extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <div className="ant-layout-breadcrumb">
                    <Breadcrumb>
                        <Breadcrumb.Item>管理后台</Breadcrumb.Item>
                        <Breadcrumb.Item>首页</Breadcrumb.Item>
                        <Breadcrumb.Item>欢迎</Breadcrumb.Item>
                    </Breadcrumb>
                </div>
                <div className="ant-layout-container">
                    <div className="ant-layout-content">
                        <div style={{ height: 590}}>
                            <img src="../images/main_pic.gif" style={{width:"465px",height:"129px",border:"0px", position:"absolute", left:"50%",top:"50%",marginLeft:-237,marginTop:-65}}  />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
};