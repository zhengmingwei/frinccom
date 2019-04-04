import React from 'react';
import SubPage from 'modules/common/SubPage';
import {refreshUserList} from 'actions/UserActions';
import {connect} from 'react-redux';
import {Icon, Table, Row, Col, Button} from 'antd';
import UserList from './UserList';
import UserSearch from './UserSearch';

/**
 * User: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
class UserManager extends React.Component {
    constructor(props) {
        super(props);
    }

    view(id){
        this.history.push('/manager/user/detail/'+ id);
    }

    edit(id){
        console.log("edit user0",id)
        this.history.push('/manager/user/form/'+ id);
    }

    componentDidMount() {
        this.props.dispatch(refreshUserList());
    }

    render() {

        return (
            <div>
                <SubPage breadcrumb="/首页/后台管理/用户管理">
                    <Row gutter={16} className="bottom-space">
                        <Col>
                            <UserSearch {...this.props}/>
                        </Col>
                    </Row>
                    <Row gutter={16}>
                        <Col>
                            <UserList {...this.props} onEdit={this.edit} onView={this.view} />
                        </Col>
                    </Row>
                </SubPage>
            </div>
        )
    }
};

export default connect(
    state => {
        let {userList, userStatus, userCriteria} = state;
        return {
            userList, userStatus, userCriteria
        }
    }
)(UserManager);