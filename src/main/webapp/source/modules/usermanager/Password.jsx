import React from "react";
import {connect} from 'react-redux';
import {changePwd} from "actions/UserActions";
import SubPage from 'modules/common/SubPage';
import {Form, Input, Checkbox, Select, Button} from "antd";
const FormItem = Form.Item;
const createForm = Form.create;

class Password extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        // this.props.dispatch(getUser(this.props.params.id));
    }

    reset() {
        this.props.form.resetFields();
    }

    changePwd() {
        let rows = this.props.form.getFieldsValue();
        const {password, pwd,oldpassword} = rows;
        if (password != pwd) {
            alert("两次输入的密码不一致！")
        } else {
            this.props.dispatch(changePwd(password, oldpassword));
        }
    }

    render() {
        const {getFieldDecorator} = this.props.form;
        const formItemLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 5},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 12},
            },
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 24,
                    offset: 0,
                },
                sm: {
                    span: 14,
                    offset: 6,
                },
            },
        };
        const {distributorOptions} = this.props;

        return (
            <SubPage breadcrumb="/后台管理/用户管理/修改密码">
			<Form>

				<FormItem
                    {...formItemLayout}
					label="输入原密码"
				>
                    {getFieldDecorator('oldpassword', {
                        validateTrigger: ['onChange', 'onBlur'],
                        rules: [{
                            required: true,
                            whitespace: false,
                            message: "请输入原密码.",
                        }],
                    })(
						<Input placeholder="原密码"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="输入新密码"
				>
                    {getFieldDecorator('password', {
                        validateTrigger: ['onChange', 'onBlur'],
                        rules: [{
                            required: true,
                            whitespace: false,
                            message: "请输入新密码.",
                        }],
                    })(
						<Input placeholder="新密码"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="再输入新密码"
				>
                    {getFieldDecorator('pwd', {
                        validateTrigger: ['onChange', 'onBlur'],
                        rules: [{
                            required: true,
                            whitespace: false,
                            message: "新密码.",
                        }],
                    })(
						<Input placeholder="新密码"/>
                    )}
				</FormItem>
                <FormItem {...tailFormItemLayout}>
                    <Button type="primary" htmlType="button" size="large"
                            onClick={() => this.changePwd()}>确定</Button>

                    <Button type="primary" htmlType="reset" size="large" onClick={() => this.reset()}
                            style={{marginLeft: 8}}>重置</Button>
                </FormItem>
			</Form>
            </SubPage>
        )
    }
}
;
Password = createForm()(Password);
export default connect(
    state => {
        return {
        }
    }
)(Password);Password;