<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('Edit User'), ['action' => 'edit', $user->id]) ?> </li>
        <li><?= $this->Form->postLink(__('Delete User'), ['action' => 'delete', $user->id], ['confirm' => __('Are you sure you want to delete # {0}?', $user->id)]) ?> </li>
        <li><?= $this->Html->link(__('List Users'), ['action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New User'), ['action' => 'add']) ?> </li>
    </ul>
</nav>
<div class="users view large-9 medium-8 columns content">
    <h3><?= h($user->id) ?></h3>
    <table class="vertical-table">
        <tr>
            <th><?= __('Nickname') ?></th>
            <td><?= h($user->nickname) ?></td>
        </tr>
        <tr>
            <th><?= __('Address') ?></th>
            <td><?= h($user->address) ?></td>
        </tr>
        <tr>
            <th><?= __('ProfileImage') ?></th>
            <td><?= h($user->profileImage) ?></td>
        </tr>
        <tr>
            <th><?= __('Description') ?></th>
            <td><?= h($user->description) ?></td>
        </tr>
        <tr>
            <th><?= __('Works') ?></th>
            <td><?= h($user->works) ?></td>
        </tr>
        <tr>
            <th><?= __('Lat') ?></th>
            <td><?= h($user->lat) ?></td>
        </tr>
        <tr>
            <th><?= __('Long') ?></th>
            <td><?= h($user->long) ?></td>
        </tr>
        <tr>
            <th><?= __('CreateDate') ?></th>
            <td><?= h($user->createDate) ?></td>
        </tr>
        <tr>
            <th><?= __('UpdateDate') ?></th>
            <td><?= h($user->updateDate) ?></td>
        </tr>
        <tr>
            <th><?= __('DelFlg') ?></th>
            <td><?= h($user->delFlg) ?></td>
        </tr>
        <tr>
            <th><?= __('UserId') ?></th>
            <td><?= $this->Number->format($user->userId) ?></td>
        </tr>
        <tr>
            <th><?= __('Gender') ?></th>
            <td><?= $this->Number->format($user->gender) ?></td>
        </tr>
        <tr>
            <th><?= __('Birthday') ?></th>
            <td><?= h($user->birthday) ?></td>
        </tr>
    </table>
</div>
