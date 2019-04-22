import React from 'react';
import SubPage from 'modules/common/SubPage';
import {refreshUserList2} from 'actions/UserActions';
import {connect} from 'react-redux';
import {Icon, Table, Row, Col, Button} from 'antd';
import UserList2 from './UserList2';
import UserSearch2 from './UserSearch2';

/**
 * User: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
class UserManager2 extends React.Component {
    constructor(props) {
        super(props);
    }

    view(id){
        this.history.push('/manager/user/detail/'+ id);
    }

    edit(id){
        console.log("edit user0",id)
        this.history.push('/manager/user/form2/'+ id);
    }

    componentDidMount() {
        this.props.dispatch(refreshUserList2());
    }

    render() {

        return (
            <div>
                <SubPage breadcrumb="/审核管理中心/用户管理">
                    <Row gutter={16} className="bottom-space">
                        <Col>
                            <UserSearch2 {...this.props}/>
                        </Col>
                    </Row>
                    <Row gutter={16}>
                        <Col>
                            <UserList2 {...this.props} onEdit={this.edit} onView={this.view} />
                        </Col>
                    </Row>
                </SubPage>
            </div>
        )
    }
};

export default connect(
    state => {
        let {userList2, userStatus, userCriteria} = state;
        return {
            userList2, userStatus, userCriteria
        }
    }
)(UserManager2);