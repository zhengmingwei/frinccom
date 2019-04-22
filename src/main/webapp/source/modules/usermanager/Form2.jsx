import React from "react";
import {connect} from 'react-redux';
import {saveUser,getUser} from "actions/UserActions";
import {allDistributorOptions} from "actions/DistributorActions";
import SubPage from 'modules/common/SubPage';
import {Form, Input, Checkbox, Select, Button} from "antd";
import {showModalDialog} from "actions/CommonAction";
import ImgUpload from "modules/common/ImgUpload";
import moment from "../commodity/CommodityForm";


const FormItem = Form.Item;
const CheckboxGroup = Checkbox.Group;
const createForm = Form.create;
const Option = Select.Option;

class UserAdd2 extends React.Component {

    constructor(props) {
        super(props);
    }
     
    shouldComponentUpdate(nextProps, nextState) {
        const statusId = nextProps.params.id;
        const oldStatusId = this.props.params.id;

        if (statusId != oldStatusId){
            this.props.dispatch(getUser(statusId));
            return false;
        }
        return true;
    }

    componentDidMount() {
        this.props.dispatch(allDistributorOptions());
        this.props.dispatch(getUser(this.props.params.id));
    }

    reset() {
        this.props.form.resetFields();
    }

    saveUser() {
        let rows1 = this.props.form.getFieldsValue();
        E.addOneTimeEventListener("touserlist",  (e) => {
            this.props.history.push('/manager/user/list2');
        })
        let rows = this.props.form.getFieldsValue();
        console.log(rows)
        const {roleIds} = rows;
        let roles = [];
        roleIds.map((roleId)=>{ let role = {}; role.id = roleId; roles.push(role) });
        rows.roles = roles;
        this.props.dispatch(saveUser(rows));
    }

    render() {
        const {getFieldDecorator} = this.props.form;

        let fbusinessLicense = "aa";

        console.log(this.props.form)
        const {selectedUser:{id, name= "", password="",idCard="", mail="",businessLicense="", phone="", distributorId="", roles=[]}} = this.props;
        fbusinessLicense = businessLicense;

        let roleIds = [];
        roles.forEach((item) => roleIds.push(item.id));
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
        const options = [
            { label: '审核管理员', value: 'ROLE_AUDITOR' },
            { label: '客服人员', value: 'ROLE_CUSTOMER' },
        ];
        const {distributorOptions} = this.props;


        const {form1} = this.props.form;

        let distributorOptionsObj = [];
        distributorOptionsObj = distributorOptions.map((item) => {

            return <Option key={item.ID}>{item.NAME}</Option>;
        });
        return (
            <SubPage breadcrumb="/审核管理中心/分销商用户管理/修改用户">
			<Form>
                {getFieldDecorator('id', {
                    initialValue: id,
                })(
                    <Input type="hidden"/>
                )}
				<FormItem
                    {...formItemLayout}
					label="用户姓名"

				>
                    {getFieldDecorator('name', {initialValue: name,})(
						<Input placeholder="用户姓名"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="密码"

				>
                    {getFieldDecorator('password', {initialValue: 123456,})(
						<Input placeholder="用户密码"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="邮箱"
				>
                    {getFieldDecorator('mail', {initialValue: mail,})(
						<Input placeholder="E-mail"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="身份证"
				>
                    {getFieldDecorator('idCard', {initialValue: idCard,})(
						<Input placeholder="身份证"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="所属分销商"
				>
                    {getFieldDecorator('distributorId', {initialValue: distributorId,})(
                        <Select >
                            {distributorOptionsObj}
                        </Select>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="联系电话"
				>
                    {getFieldDecorator('phone', {initialValue: phone,})(
						<Input placeholder="联系电话"/>
                    )}
				</FormItem>
				 <FormItem
                    {...formItemLayout}
                    label="企业资质"
                >
                    {getFieldDecorator('businessLicense', {
                        valuePropName: 'fileList',
                        getValueFromEvent: this.normFile,
                    })(
                        <ImgUpload name="uploadFile" action="/incc/file/upload/1/1" initialValue={fbusinessLicense} >

                        </ImgUpload>
                    )}
                </FormItem>
				<FormItem
                    {...formItemLayout}
					label="角色分配"
				>
                    {getFieldDecorator('roleIds', {initialValue: roleIds})(
                        <CheckboxGroup options={options}/>
                    )}
				</FormItem>
                <FormItem {...tailFormItemLayout}>
                    <Button type="primary" htmlType="button" size="large"
                            onClick={() => this.saveUser()}>确定</Button>

                    <Button type="primary" htmlType="reset" size="large" onClick={() => this.reset()}
                            style={{marginLeft: 8}}>重置</Button>
                </FormItem>
               
			</Form>
            </SubPage>
        )
    }
}
;
UserAdd2 = createForm()(UserAdd2);
export default  connect(
    state => {
        let {distributorOptions, selectedUser} = state;
        return {
            distributorOptions, selectedUser
        }
    }
)(UserAdd2);