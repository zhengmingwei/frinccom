import React from 'react';
import ReactDOM from 'react-dom';
import { Breadcrumb } from 'antd';
/**
 * User: zyj
 * Date: 16/7/19.
 * Time: 下午3:26.
 */
export default class SubPage extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const {breadcrumb} = this.props;
        const items = breadcrumb.match(/\/[^\/]+/g);
        let breadcrumbItems = [];
        items.map((item,index)=>{breadcrumbItems.push(<Breadcrumb.Item key={index}>{item.substr(1)}</Breadcrumb.Item>)})

        return (
            <div>
                <div className="ant-layout-breadcrumb">
                    <Breadcrumb>
                        {breadcrumbItems}
                    </Breadcrumb>
                </div>
                <div className="ant-layout-container">
                    <div className="ant-layout-content">
                        <div style={{ height: 590}}>
                            {this.props.children}
                        </div>
                    </div>
                </div>
            </div>
        )
    }
};