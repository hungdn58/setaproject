<?php
namespace App\Controller;

use App\Controller\AppController;
use Cake\ORM\TableRegistry;

/**
 * Users Controller
 *
 * @property \App\Model\Table\UsersTable $Users
 */
class UsersController extends AppController
{

    /**
     * Index method
     *
     * @return \Cake\Network\Response|null
     */
    public function index()
    {
        //$this->Users->findAll();
        $this->viewBuilder()->layout('json');
        //$users = $this->paginate($this->Users);

        //$this->set(compact('users'));
        //$this->set('_serialize', ['users']);
        $data = Array(
            "name" => "Saad Imran",
            "age" => 19
        );
        $data = $this->Users->find('all');
        
        $output = [];
        if($data) {
            $output = ['result' => 1];
            foreach ($data as $item) {
                $output[] = [
                    'user' => [
                        'profileImage'  =>  $item->profileImage,
                        'nickname'  =>  $item->nickname,
                        'address'   =>  $item->address,
                        'replyTo'   =>  ['userID' => $item->userId, 'userName' => $item->nickname],
                        'content'   =>  $item->description
                    ]
                ];
                
            }
            $output[] = ['totalCount' => $data->count()];
        } else {
            $output = ['result' => 0, 'reason' => 'Không lấy được dữ liệu'];
        }
                
        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }

    public function search()
    {
        $this->viewBuilder()->layout('json');
        $gender = $this->request->query['gender'];
        $year_from = $this->request->query['year_from'];
        $year_to = $this->request->query['year_to'];

        $data = $this->Users->find()->where(['dateDiff(NOW(),Users.birthday) >' => $year_from])
                                    ->andWhere(['dateDiff(NOW(),Users.birthday) <' => $year_to])
                                    ->andWhere(['Users.gender' => $gender]);

        $output = [];
        if($data) {
            $output = ['result' => 1];
            $array = array();

            foreach ($data as $user) {

                $array[] = [
                    'user' => [
                        'profileImage'  =>  $user->profileImage,
                        'userID'  =>  $user->userId,
                    ]
                ];
            }
            $output[] = ['data' => $array];
            $output[] = ['totalCount' => $data->count()];
        } else {
            $output = ['result' => 0, 'reason' => 'Không lấy được dữ liệu'];
        }
                
        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }

    /**
     * View method
     *
     * @param string|null $id User id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $user = $this->Users->get($id, [
            'contain' => ['Bookmarks']
        ]);

        $this->set('user', $user);
        $this->set('_serialize', ['user']);
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $user = $this->Users->newEntity();
        if ($this->request->is('post')) {
            $user = $this->Users->patchEntity($user, $this->request->data);
            if ($this->Users->save($user)) {
                $this->Flash->success(__('The user has been saved.'));
                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The user could not be saved. Please, try again.'));
            }
        }
        $this->set(compact('user'));
        $this->set('_serialize', ['user']);
    }

    /**
     * Edit method
     *
     * @param string|null $id User id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($userId = null)
    {
        $user = $this->Users->find()->where(['userId' => $userId]);
        $this->viewBuilder()->layout('json');
        $output = [];

        if ($this->request->is(['patch', 'post', 'put'])) {
            $user = $this->Users->patchEntity($user, $this->request->data);
            if ($this->Items->save($user)) {
                $this->Flash->success(__('The item has been saved.'));
                $output = ['result' => 1];
                // return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The item could not be saved. Please, try again.'));
                $output[] = ['result' => 0, 'reason' => 'post bị lỗi'];
            }
            $this->set('data', json_encode($output));
            $this->render('/General/SerializeJson/');
        }
        $this->set(compact('user'));
        $this->set('_serialize', ['user']);
    }

    /**
     * Delete method
     *
     * @param string|null $id User id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete()
    {
        $this->viewBuilder()->layout('json');
        $this->request->allowMethod(['post', 'delete']);
        $id = $this->request->query['id'];
        $user = $this->Users->find()->where(['userId' => $id]);
        $output = [];
        if ($this->Users->delete($user)) {
            $this->Flash->success(__('The user has been deleted.'));
            $output = ['result' => 1];
        } else {
            $this->Flash->error(__('The user could not be deleted. Please, try again.'));
            $output[] = ['result' => 0, 'reason' => 'post bị lỗi'];
        }
        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }

    public function getProfile($userId = null)
    {
        $this->viewBuilder()->layout('json');

        $user = $this->Users->find()->where(['userId' => $userId])->first();
        $output = [];

        if ($user) {
            # code...
            $output = ['result' => 1];
            $array = array();
            $output[] = [
                'data' => [
                    'profileImage' => $user->profileImage,
                    'nickname' => $user->nickname,
                    'gender' => $user->gender,
                    'birthday' => $user->birthday,
                    'address' => $user->address,
                    'description' => $user->description,
                ]
            ];
        }else{
            $output = ['result' => 0, 'reason' => 'không lấy được dữ liệu'];
        }

        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }

}
