import React from "react";
import {connect} from 'react-redux';
import {getUser} from "actions/UserActions";
import {allDistributorOptions} from "actions/DistributorActions";
import SubPage from 'modules/common/SubPage';
import {Form, Input, Checkbox, Select, Button} from "antd";
import {showModalDialog} from "actions/CommonAction";
const FormItem = Form.Item;
const CheckboxGroup = Checkbox.Group;
const createForm = Form.create;
const Option = Select.Option;

class UserAdd extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.dispatch(getUser(this.props.params.id));
    }


    render() {
        const {getFieldDecorator} = this.props.form;
        const {selectedUser:{ name= "", idCard="", mail="", phone="", distributor={}, roles=[]}} = this.props;
        let roleNames = "";
        roles.forEach((item) => roleNames = roleNames +" "+item.name);
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

        return (
            <SubPage breadcrumb="/首页/后台管理/用户管理">
                <Form>
                    <FormItem
                        {...formItemLayout}
                        label="用户姓名"

                    >
                        {name}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="邮箱"
                    >
                        {mail}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="身份证"
                    >
                        {idCard}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="所属分销商"
                    >
                        {distributor.name}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="联系电话"
                    >
                        {phone}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="角色分配"
                    >
                        {roleNames}
                    </FormItem>

                </Form>
            </SubPage>
        )
    }
}
;
UserAdd = createForm()(UserAdd);
export default  connect(
    state => {
        let {selectedUser} = state;
        return {
            selectedUser
        }
    }
)(UserAdd);