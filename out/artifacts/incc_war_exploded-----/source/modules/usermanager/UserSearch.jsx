import React from 'react';
import ReactDOM from 'react-dom';

import SubPage from 'modules/common/SubPage';

import {refreshUserList,userCriteriaChanged} from 'actions/UserActions';

import { Form, Input, Row, Col, Button, Radio, Select,Tooltip } from 'antd';
import {showModalDialog} from 'actions/CommonAction';
const FormItem = Form.Item;
const RadioGroup = Radio.Group;
const createForm = Form.create;

/**
 * User: zyj
 * Date: 16/12/21.
 * Time: 上午10:26.
 */
class UserSearch extends React.Component {

    constructor(props) {
        super(props);
    }

    search(){
        let rows =this.props.form.getFieldsValue();
        this.props.dispatch( refreshUserList(rows));
        this.props.dispatch(userCriteriaChanged(rows));
    }

    reset(){
        this.props.form.resetFields();
        this.props.dispatch(userCriteriaChanged());
    }

    //新增用户begin
    saveUser(){
        let rows =this.props.form.getFieldsValue();
        // console.log("***********");
        // console.log(rows);
        // console.log("***********");
    }
    addUserShow(){
        const { getFieldDecorator } = this.props.form;
        let title = "新增系统用户";
        let okText = "保存";
        let cancelText = "取消";
        const contentObj = <Form horizontal inline>
            <Row gutter={16}>
                <Col  className="gutter-row" span={12}>
                    <FormItem
                        label="手机号"
                        labelCol={{ span: 8 }}
                        wrapperCol={{ span: 16 }}
                    >
                        {getFieldDecorator('username', {})(
                            <Input placeholder="手机号" />
                        )}
                    </FormItem>
                </Col>
                <Col  className="gutter-row" span={12}>
                    <FormItem
                        label="密码"
                        labelCol={{ span: 8 }}
                        wrapperCol={{ span: 16 }}
                    >
                        {getFieldDecorator('password', {})(
                        <Input placeholder="密码" type="password"/>
                        )}
                    </FormItem>
                </Col>
            </Row>
            <Row gutter={16}>
                <Col  className="gutter-row" span={12}>
                    <FormItem
                        label="姓名"
                        labelCol={{ span: 8 }}
                        wrapperCol={{ span: 16 }}
                    >
                        {getFieldDecorator('realname', {})(
                        <Input placeholder="姓名" />
                        )}
                    </FormItem>
                </Col>
                <Col  className="gutter-row" span={12}>
                    <FormItem
                        label="性别"
                        labelCol={{ span: 8 }}
                        wrapperCol={{ span: 16 }}
                    >
                        {getFieldDecorator('sex', {})(
                        <Select style={{ width: '129',margin:'0 0 0 7px'}} default="男">
                            <Option value="1">男</Option>
                            <Option value="2">女</Option>
                        </Select>
                        )}
                    </FormItem>
                </Col>
            </Row>
            <Row gutter={16}>
                <Col  className="gutter-row" span={12}>
                    <FormItem
                        label="身份证"
                        labelCol={{ span: 8 }}
                        wrapperCol={{ span: 16 }}
                    >
                        {getFieldDecorator('cardNo', {})(
                        <Input placeholder="身份证" />
                        )}
                    </FormItem>
                </Col>
                <Col  className="gutter-row" span={12}>
                    <FormItem
                        label="E-mail"
                        labelCol={{ span: 8 }}
                        wrapperCol={{ span: 16 }}
                    >
                        {getFieldDecorator('email', {})(
                        <Input placeholder="E-mail" />
                        )}
                    </FormItem>
                </Col>
            </Row>
        </Form>
        self = this;
        const dialogConfig = {visible:true, title:title, okText:okText, cancelText: cancelText, content:contentObj, okHandler(){self.saveUser()}};
        this.props.dispatch(showModalDialog(dialogConfig));
    }
    //新增用户end
    render() {
        const { getFieldDecorator } = this.props.form;
        const formItemLayout = {
            labelCol: { span: 9 },
            wrapperCol: { span: 15 },
            width:"100%",
        };
        return (
            <Form>
                <Row gutter={40}>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="用户姓名"
                        >
                            {getFieldDecorator('name', {})(
                                <Input placeholder="用户姓名        " />
                            )}
                        </FormItem>
                    </Col>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="邮箱"
                        >
                            {getFieldDecorator('mail', {})(
                                <Input placeholder="邮箱        " />

                            )}
                        </FormItem>
                    </Col>
                    <Col span={8}>
                        <div className="ant-row ant-form-item"><div className="ant-col-9 ant-form-item-label">&nbsp;</div></div>
                    </Col>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="联系电话"
                        >
                            {getFieldDecorator('phone', {})(
                                <Input placeholder="联系电话       " />
                            )}
                        </FormItem>
                    </Col>
                    <Col span={8}>
                        <FormItem
                            {...formItemLayout}
                            label="分销商企业名称"
                        >
                            {getFieldDecorator('distributor.name', {})(
                                <Input placeholder="分销商企业名称       " />
                            )}
                        </FormItem>
                    </Col>
                    <Col span={8}>
                        <Button type="primary" htmlType="submit" onClick={()=>this.search()}>搜索</Button>&nbsp;
                    </Col>

                </Row>
            </Form>
        )
    }
}
;
UserSearch = createForm()(UserSearch);
export default UserSearch;