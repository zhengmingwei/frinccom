import React from 'react';
import {Icon, Table, Modal, Row, Col} from 'antd';
import {refreshUserList, changeUserStatus, delUser} from 'actions/UserActions';
import {showModalDialog} from 'actions/CommonAction';
import DelConfirm from '../common/DelConfirm';
const confirm = Modal.confirm;
/**
 * User: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
export default class UserList extends React.Component {
    constructor(props) {
        super(props);
    }

    view(id){
        this.props.onView(id);
    }

    edit(id){
        this.props.onEdit(id);
    }

    delete(id, userCriteria, pageNum){
        this.props.dispatch(delUser(id, userCriteria, pageNum));
        // this.props.onDelete(id);
    }

    disableUser(record, index) {
        const user = record.user;
        let contentText = "";
        let userstatus = 1;
        if (user.status === 1) {
            contentText = "是否禁用" + user.username;
            userstatus = 2;
        } else if (user.status === 2) {
            contentText = "是否启用" + user.username;
            userstatus = 1;
        }
        let self = this;
        const dialogConfig = {
            visible: true/*, okText: "同意"*/, content: contentText, okHandler(){
                // self.onOk()
                let data = {};
                Object.assign(data, record);
                data.user.status = userstatus;
                self.props.dispatch(changeUserStatus(data));
            }
        };
        this.props.dispatch(showModalDialog(dialogConfig));



    }

    render() {

        const {userList} = this.props;

        const {userCriteria} = this.props;

        const columns = [
            {title: '姓名', dataIndex: 'name', key: 'name'},
            {title: '邮箱', dataIndex: 'mail', key: 'mail'},
            {title: '所属分销商', dataIndex: 'distributor.name', key: 'distributorname'},
            {title: '联系电话', dataIndex: 'phone', key: 'phone'},
            {title: '角色', dataIndex: 'roles', key: 'roles', render:(text, roles, index) =>(

                      text? text.reduce(function(prev, current, index, array){
                          if (index === 0){
                              return (current.name);
                          }
                          else if (index === array.length - 1){
                              return prev + ', ' + current.name;
                          }
                          else {
                              return prev + ', ' + current.name;
                          }
                      },''):""
                // console.log(text)
                //     text&&text.length>0? text.reduce((item)=>{return ""+ item.name}):""

                 // const rolename =  text.map((role)=>{role.name})
                )},
            {
                title: '操作', dataIndex: '', key: 'x', render: (text, record, index) => (

                <span>
                    <a onClick={()=>this.view(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 查看</a>
                    <a onClick={()=>this.edit(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 修改</a>
                    <DelConfirm onOk = {()=>this.delete(record.id, userCriteria, userList.pageNum)}/>
                </span>
            ),
            },
        ];

        const pagination = {
            total: userList.total,
            pageSize: userList.pageSize,
            current: userList.pageNum,
            onChange: (page) => {
                this.props.dispatch(refreshUserList(userCriteria, {"pageNum": page}));
            },
        };

        return (
            <div>
                <Table
                    columns={columns}
                    bordered
                    dataSource={userList.list}
                    pagination={pagination}
                    className="table"
                />
            </div>
        )
    }
};
