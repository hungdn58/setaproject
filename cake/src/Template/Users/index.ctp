<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('New User'), ['action' => 'add']) ?></li>
    </ul>
</nav>
<div class="users index large-9 medium-8 columns content">
    <h3><?= __('Users') ?></h3>
    <table cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th><?= $this->Paginator->sort('userId') ?></th>
                <th><?= $this->Paginator->sort('nickname') ?></th>
                <th><?= $this->Paginator->sort('address') ?></th>
                <th><?= $this->Paginator->sort('profileImage') ?></th>
                <th><?= $this->Paginator->sort('description') ?></th>
                <th><?= $this->Paginator->sort('gender') ?></th>
                <th><?= $this->Paginator->sort('birthday') ?></th>
                <th><?= $this->Paginator->sort('works') ?></th>
                <th><?= $this->Paginator->sort('lat') ?></th>
                <th><?= $this->Paginator->sort('long') ?></th>
                <th><?= $this->Paginator->sort('createDate') ?></th>
                <th><?= $this->Paginator->sort('updateDate') ?></th>
                <th><?= $this->Paginator->sort('delFlg') ?></th>
                <th class="actions"><?= __('Actions') ?></th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($users as $user): ?>
            <tr>
                <td><?= $this->Number->format($user->userId) ?></td>
                <td><?= h($user->nickname) ?></td>
                <td><?= h($user->address) ?></td>
                <td><?= h($user->profileImage) ?></td>
                <td><?= h($user->description) ?></td>
                <td><?= $this->Number->format($user->gender) ?></td>
                <td><?= h($user->birthday) ?></td>
                <td><?= h($user->works) ?></td>
                <td><?= h($user->lat) ?></td>
                <td><?= h($user->long) ?></td>
                <td><?= h($user->createDate) ?></td>
                <td><?= h($user->updateDate) ?></td>
                <td><?= h($user->delFlg) ?></td>
                <td class="actions">
                    <?= $this->Html->link(__('View'), ['action' => 'view', $user->id]) ?>
                    <?= $this->Html->link(__('Edit'), ['action' => 'edit', $user->id]) ?>
                    <?= $this->Form->postLink(__('Delete'), ['action' => 'delete', $user->id], ['confirm' => __('Are you sure you want to delete # {0}?', $user->id)]) ?>
                </td>
            </tr>
            <?php endforeach; ?>
        </tbody>
    </table>
    <div class="paginator">
        <ul class="pagination">
            <?= $this->Paginator->prev('< ' . __('previous')) ?>
            <?= $this->Paginator->numbers() ?>
            <?= $this->Paginator->next(__('next') . ' >') ?>
        </ul>
        <p><?= $this->Paginator->counter() ?></p>
    </div>
</div>
